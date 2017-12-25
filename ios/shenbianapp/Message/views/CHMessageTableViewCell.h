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
@end
