//
//  CHMessageTableViewCell.h
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017 . All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CHMessageModel.h"
@interface CHMessageTableViewCell : UITableViewCell
@property(nonatomic,strong) NSIndexPath *indexPath;
@property(nonatomic,strong) CHMessageModel *model;

@property(nonatomic,strong)UIImageView *headImage;
@property(nonatomic,strong)UILabel *userNameLabel;
@property(nonatomic,strong)UILabel *briefMsgLabel;
@property(nonatomic,strong)UILabel *lastTimeLabel;
@end
